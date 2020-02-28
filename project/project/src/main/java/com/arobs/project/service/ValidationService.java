package com.arobs.project.service;

import com.arobs.project.exception.ValidationException;
;
import java.util.List;

public class ValidationService {

    public static <T> T safeGetUniqueResult(List resultList) throws ValidationException {
        if (resultList == null || resultList.isEmpty()) return null;
        if (resultList.size() > 1) {
            throw new ValidationException("Too many results ");
        }

        return (T) resultList.get(0);
    }
}
