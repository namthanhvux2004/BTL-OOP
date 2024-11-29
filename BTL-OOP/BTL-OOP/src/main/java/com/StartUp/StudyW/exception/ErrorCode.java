package com.StartUp.StudyW.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode
{
    INVALID_KEY(1001,"Invalid key!"),
    UNCATEGORIED_EXCEPTION(9999, "Uncategorized error!"),
    DESCRIPTION_EXISTED(1002, "Subject exists!"),
    DECK_EXISTED(1003, "Topic exists!")
    ;
    int code;
    String message;
}
