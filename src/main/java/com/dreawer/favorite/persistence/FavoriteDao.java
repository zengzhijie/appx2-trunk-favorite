package com.dreawer.favorite.persistence;

import static com.dreawer.favorite.constants.DomainConstants.*;
import static com.dreawer.favorite.constants.DAOConstants.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import org.springframework.stereotype.Repository;
import com.dreawer.favorite.domain.Favorite;
import com.dreawer.favorite.lang.ContentStatus;


/**
 * <CODE>FavoriteDao</CODE> 收藏 DAO 类，负责对收藏实例数据进行访问和操作。
 *
 * @author zengzhijie
 * @version 1.0
 * @since Favorite 1.0
 */
@Repository(FAVORITE_DAO)
public class FavoriteDao extends MyBatisBaseDao<Favorite> {

    /**
     * 保存收藏信息。
     *
     * @param favorite 收藏信息。
     * @return 成功保存的记录数。
     * @author zengzhijie
     * @since 1.0
     */
    public int save(Favorite favorite) {
        return insert("save", favorite);
    }

    /**
     * 更新收藏信息状态（正常或删除）。
     *
     * @param favorite 收藏信息。
     * @return 成功更新的记录数。
     * @author zengzhijie
     * @since 1.0
     */
    public int updateFavorite(Favorite favorite) {
        return update("updateFavorite", favorite);
    }

    /**
     * 查询指定ID号的收藏信息。
     *
     * @param id 收藏ID号。
     * @return 收藏内容。如果存在返回收藏内容，否则返回null。
     * @author zengzhijie
     * @since 1.0
     */
    public Favorite findFavorite(String id) {
        return selectOne("findFavoriteById", id);
    }

    /**
     * 查询指定收藏者和指定内容所对应的收藏信息。
     *
     * @param collectorId 收藏者。
     * @param contentId   被收藏内容。
     * @return 收藏信息。如果存在返回收藏信息，否则返回null。
     * @author zengzhijie
     * @since 1.0
     */
    public Favorite findFavorite(String collectorId, String contentId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(COLLECTOR, collectorId);
        params.put(CONTENT_ID, contentId);
        return selectOne("findFavoriteByCollector", params);
    }

    /**
     * 查询指定收藏者的收藏信息。
     *
     * @param collectorId  收藏人的id。
     * @param startTime  查询纪录开始时间。
     * @param maxResults 分页记录总数。
     * @return 收藏列表。如果存在返回收藏列表，否则返回长度为0的收藏列表。
     * @author zengzhijie
     * @since 1.0
     */
    public List<Favorite> findFavorites(String collectorId, Timestamp startTime, Integer maxResults) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(COLLECTOR, collectorId);
        params.put(STATUS, ContentStatus.DEFAULT);
        params.put(START_TIME, startTime);
        params.put(MAX_RESULTS, maxResults);
        return selectList("findFavorites", params);
    }

}
