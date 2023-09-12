package com.declare.web;

import com.declare.biz.DeclareService;
import com.declare.common.BizError;
import com.declare.common.model.Result;
import com.declare.common.utils.AssertUtils;
import com.declare.dao.dataobject.DeclareEntity;
import com.declare.web.request.DeclareCreateRequest;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Validated
@RestController
@RequestMapping("/api/declare")
public class DeclareController {

    @Resource
    private DeclareService declareService;

    @RequestMapping("create")
    @SneakyThrows
    public Result<String> create(@Valid @RequestBody DeclareCreateRequest declareCreateRequest) {

        declareCreateRequest.setUserId(123L);
        declareService.create(declareCreateRequest);

        return Result.success(declareCreateRequest.getBuildCompanyName());
    }

    @RequestMapping("list")
    public Result<Object> list() {
        DeclareEntity declare = new DeclareEntity();
        Result<List<DeclareEntity>> listResult = declareService.pageList(declare);
        List<DeclareEntity> data = listResult.getData();
        return Result.success(data);
    }

    @RequestMapping("myDeclare")
    public Result<Object> myDeclare() {
        DeclareEntity declare = new DeclareEntity();
        declare.setUserId(123L);
        Result<List<DeclareEntity>> listResult = declareService.pageList(declare);
        List<DeclareEntity> data = listResult.getData();
        return Result.success(data);
    }

    @RequestMapping("detail")
    public Result<String> detail() {
        return Result.success();
    }

    @RequestMapping("pass")
    public Result<String> pass(@RequestParam("declareId") Long declareId) {
        AssertUtils.notNull(declareId, BizError.SELECT_DECLARE_RECORD);
        declareService.pass(declareId);
        return Result.success();
    }

    @RequestMapping("reject")
    public Result<String> reject(@RequestParam("declareId") Long declareId) {
        AssertUtils.notNull(declareId, BizError.SELECT_DECLARE_RECORD);
        declareService.reject(declareId);
        return Result.success();
    }


}
