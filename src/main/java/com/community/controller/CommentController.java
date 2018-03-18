package com.community.controller;

import com.community.dao.CommentDao;
import com.community.entity.Comment;
import com.community.vo.result.BaseDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentDao commentDao;
    @RequestMapping("/insert")
    public @ResponseBody BaseDataResult insert(@RequestBody Comment comment) {
        BaseDataResult result = new BaseDataResult();
        boolean insert = commentDao.insert(comment);
        if (insert) {
            result.setSuccess(true);
            result.setData(commentDao.findById(comment.getId()));
        }
        return result;
    }
}
