package com.wzt.service;

import com.wzt.NotFoundException;
import com.wzt.dao.TypeRepository;
import com.wzt.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Create By coder_tao on 2020/3/30 0030
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;


    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);  // 增
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.findOne(id);  // 查单个
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);    // 查多个
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {    // 更新
        Type type1 = typeRepository.findOne(id);
        if (type1 == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,type1);
        return typeRepository.save(type1);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {           // 删
        typeRepository.delete(id);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        // 首页分类栏目排序倒序
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }


}
