package com.declare.biz;

import com.declare.biz.enums.DeclareStatusEnum;
import com.declare.common.BizError;
import com.declare.common.model.Result;
import com.declare.common.utils.AssertUtils;
import com.declare.dao.DeclareExpenseItemRepository;
import com.declare.dao.DeclareInformationImgRepository;
import com.declare.dao.DeclareRepository;
import com.declare.dao.dataobject.DeclareEntity;
import com.declare.dao.dataobject.DeclareExpenseItemEntity;
import com.declare.dao.dataobject.DeclareInformationImgEntity;
import com.declare.web.request.DeclareCreateRequest;
import com.declare.web.request.FileInfoRequest;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class DeclareService {

    @Resource
    private DeclareRepository declareRepository;

    @Resource
    private DeclareExpenseItemRepository declareExpenseItemRepository;

    @Resource
    private DeclareInformationImgRepository declareInformationImgRepository;


    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Result<String> create(DeclareCreateRequest declareCreateRequest) {
        // 创建申报
        Long declareId = createDeclare(declareCreateRequest);
        // 保存费用信息
        saveDeclareExpenseInfo(declareCreateRequest, declareId);
        // 保存图片信息
        saveImgInfo(declareCreateRequest, declareId);
        return Result.success();
    }

    private void saveImgInfo(DeclareCreateRequest declareCreateRequest, Long declareId) {
        List<FileInfoRequest> fileInfoList = declareCreateRequest.getFileInfoList();
        List<DeclareInformationImgEntity> list = fileInfoList
                .stream()
                .map((item) -> {
                    DeclareInformationImgEntity entity = new DeclareInformationImgEntity();
                    entity.setUserId(declareCreateRequest.getUserId());
                    entity.setDeclareId(declareId);
                    entity.setType(item.getType());
                    entity.setPath(item.getPath());
                    return entity;
                })
                .toList();
        declareInformationImgRepository.saveAll(list);
    }

    private void saveDeclareExpenseInfo(DeclareCreateRequest declareCreateRequest, Long declareId) {
        DeclareExpenseItemEntity declareExpenseItem = new DeclareExpenseItemEntity();
        BeanUtils.copyProperties(declareCreateRequest, declareExpenseItem);
        declareExpenseItem.setDeclareId(declareId);
        declareExpenseItemRepository.save(declareExpenseItem);
    }

    private Long createDeclare(DeclareCreateRequest declareCreateRequest) {
        DeclareEntity declare = new DeclareEntity();
        BeanUtils.copyProperties(declareCreateRequest, declare);
        declare.setStatus(DeclareStatusEnum.SUBMIT.getValue());
        declareRepository.save(declare);
        return declare.getId();
    }

    public Result<List<DeclareEntity>> pageList(DeclareEntity declare) {

        Pageable pageable = Pageable.ofSize(123);

        Page<DeclareEntity> page = declareRepository.findAll(buildByEntity(declare), pageable);
        return Result.success(page.stream().toList());
    }

    public void pass(Long declareId) {
        DeclareEntity entity = declareRepository.findById(declareId).orElse(null);
        AssertUtils.notNull(entity, BizError.DECLARE_NOT_EXIST);
        AssertUtils.assertTrue(DeclareStatusEnum.SUBMIT.equalsValue(entity.getStatus()), BizError.DECLARE_ALREADY_PROCESSED);

        entity.setStatus(DeclareStatusEnum.PASS.getValue());
        declareRepository.save(entity);
    }

    public void reject(Long declareId) {
        DeclareEntity entity = declareRepository.findById(declareId).orElse(null);
        AssertUtils.notNull(entity, BizError.DECLARE_NOT_EXIST);
        AssertUtils.assertTrue(DeclareStatusEnum.SUBMIT.equalsValue(entity.getStatus()), BizError.DECLARE_ALREADY_PROCESSED);

        entity.setStatus(DeclareStatusEnum.REJECT.getValue());
        declareRepository.save(entity);
    }
    public static Specification<DeclareEntity> buildByEntity(DeclareEntity entity) {
        return (Root<DeclareEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // 始终为true的初始条件
            if(entity.getUserId()!=null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("userId"), entity.getUserId()));
            }

            return predicate;
        };
    }


}
