package com.declare.biz.dto;

public record CaptchaImage(String captchaCode, byte[] base64Image) {

}
