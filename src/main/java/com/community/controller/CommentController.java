package com.community.controller;

import com.community.dao.CommentDao;
import com.community.dao.UserCommentLoveDao;
import com.community.entity.Comment;
import com.community.entity.User;
import com.community.vo.NestCommentVo;
import com.community.vo.result.BaseDataResult;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserCommentLoveDao userCommentLoveDao;

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

    @RequestMapping("/nestCommentList/get/{id}")
    public @ResponseBody BaseDataResult getNestCommentList(@PathVariable("id") Integer id) {
        BaseDataResult result = new BaseDataResult();
        List<Comment> allByOwnerCommentId = commentDao.findAllByOwnerCommentId(id);
        result.setSuccess(true);
        result.setData(allByOwnerCommentId);
        return result;
    }

    @RequestMapping(value = "/nestComment/insert", method = RequestMethod.POST)
    public @ResponseBody BaseDataResult insertNestComment(NestCommentVo nestCommentVo) {
        BaseDataResult result = new BaseDataResult();
        if (StringUtils.isNotEmpty(nestCommentVo.getContent()) && StringUtils.isNotEmpty(nestCommentVo.getContent()) && StringUtils.isNotEmpty(nestCommentVo.getContent())) {
            Comment comment = new Comment();
            User user = new User();
            user.setUserId(nestCommentVo.getUserId());
            comment.setUser(user);
            comment.setContent(nestCommentVo.getContent());
            Comment targetComment = new Comment();
            targetComment.setId(nestCommentVo.getTargetCommentId());
            comment.setTargetComment(targetComment);
            boolean insert = commentDao.insert(comment);
            if (insert) {
                result.setSuccess(true);
                result.setData(commentDao.findById(comment.getId()));
            }
        } else {
            result.setSuccess(false);
            result.setData("未知异常,缺少必要的数据!");
        }
        return result;
    }
    @RequestMapping(value = "addLoveCount", method = RequestMethod.POST)
    public @ResponseBody BaseDataResult addLoveCount(Integer commentId, Integer userId) {
        BaseDataResult result = new BaseDataResult();
        if (commentId != null && userId != null) {
            Integer countByCommentIdAndUserId = userCommentLoveDao.getCountByCommentIdAndUserId(commentId, userId);
            if (countByCommentIdAndUserId > 0) {
                userCommentLoveDao.delete(commentId, userId);
                result.setData("delete");
            } else {
                userCommentLoveDao.insert(commentId, userId);
                result.setData("insert");
            }
            result.setSuccess(true);
        }
        return result;
    }
}
