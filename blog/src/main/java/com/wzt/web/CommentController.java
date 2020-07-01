package com.wzt.web;

import com.wzt.po.Comment;
import com.wzt.po.User;
import com.wzt.service.BlogService;
import com.wzt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


/**
 * Create By coder_tao on 2020/4/1 0001
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;


    @Value("${comment.avatar}")
    private String avatar;


    @GetMapping("/comments/{blogId}")
    public String comment(@PathVariable Long blogId,
                          Model model) {

        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));

        User user = (User) session.getAttribute("user");

        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

}
