package com.zzrg.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzrg.blog.dao.pojo.Tag;
import java.util.List;

/**
 * @author: ZzRG
 * @version: 1.0
 * Date: 2022/6/22
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签，前n条
     * @param limit
     * @return
     */
    List<Long> findHostTageId(int limit);

    /**
     * 通过最热标签tagid查询最热tags
     * @param tagIds
     * @return
     */
    List<Tag> findTagsByTagIds(List<Long> tagIds);

}
