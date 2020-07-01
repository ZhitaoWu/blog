package com.wzt.web;

import com.wzt.po.Type;
import com.wzt.service.BlogService;
import com.wzt.service.TypeService;
import com.wzt.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Create By coder_tao on 2020/4/1 0001
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        @PathVariable Long id , Model model) {

        List<Type> types = typeService.listTypeTop(10000);
        // 判断是否从导航进来的
        if (id == -1) {
            id = types.get(0).getId();
        }

        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);

        // 将分类的数据传到视图
        model.addAttribute("types", types);
        // 将博客的数据传到视图
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        // 确认选中的type标签
        model.addAttribute("activeTypeId", id);

        return "types";
    }
}
