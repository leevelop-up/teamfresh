package com.example.teamfresh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCode {
    SUCCESS("200", "Success"),
    NOT_FOUND_ENTITY("4001", "Not found the entity"),
    EMPTY_FILE("400", "Empty file");

    private String returnCode;
    private String returnMessage;
}
