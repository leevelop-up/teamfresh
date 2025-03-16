package com.example.teamfresh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode {
    SUCCESS("200", "Success"),
    NOT_FOUND_ENTITY("4001", "Not found the entity"),
    EMPTY_FILE("400", "Empty file"),
    PRODUCT_NOT_FOUND("1001", "상품을 찾을 수 없습니다."),
    STOCK_NOT_FOUND("1002", "재고 정보가 없습니다."),
    INSUFFICIENT_STOCK("1003", "재고가 부족하여 주문이 실패하였습니다.");
    private String returnCode;
    private String returnMessage;
}
