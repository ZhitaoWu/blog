package com.wzt.service;

import com.wzt.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create By coder_tao on 2020/3/30 0030
 */
public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id, Type type);

    void deleteType(Long id);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type getTypeByName(String name);
}
