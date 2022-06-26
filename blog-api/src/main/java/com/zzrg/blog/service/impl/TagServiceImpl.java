package com.zzrg.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzrg.blog.dao.mapper.TagMapper;
import com.zzrg.blog.dao.pojo.Tag;
import com.zzrg.blog.service.TagService;
import com.zzrg.blog.vo.Result;
import com.zzrg.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
@Service
public class TagServiceImpl implements TagService {

    //实现接口
    @Autowired(required = false)
    private TagMapper tagMapper;

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        //BeanUtils,copyProperties用于类之间的复制，相同字段复制，不同字段为null
        BeanUtils.copyProperties(tag, tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;

    }
    //copyList实现
    private List<TagVo> copyList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //MybatisPlus 无法实现多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result findHotTags(int limit) {
        /**
         * 1.实现最热标签，标签所拥有的文章数量最多
         * 2.根据tag_id 分组计数，从大到小排列取limit个
         */
        List<Long> tagIds = tagMapper.findHostTageId(limit);
        //因为id in（1,2,3） 里面不能为空所以我们需要进行判断
        //  CollectionUtils.isEmpty作用 https://blog.csdn.net/qq_42848910/article/details/105717235
        if (CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }
        //要的是tagId和tagNmae  tag对象
        //select * from tag where id in (1,2,3,4) 里面的id不能为空
        List<Tag> tagList = tagMapper.findTagsByTagIds(tagIds);
        return Result.success(tagList);
    }

    /**
     * 写文章添加标签
     * @return
     */
    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    /**
     * 导航页的标签
     * @return
     */
    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    /**
     * 标签详情
     * @param id
     * @return
     */
    @Override
    public Result findAllDetailById(long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }


}
