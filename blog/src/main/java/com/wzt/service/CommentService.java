package com.wzt.service;

import com.wzt.po.Comment;

import java.util.List;

/**
 * Create By coder_tao on 2020/4/1 0001
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
