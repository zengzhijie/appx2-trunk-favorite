package com.dreawer.favorite.service;



import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dreawer.favorite.domain.Favorite;
import com.dreawer.favorite.persistence.FavoriteDao;

import static com.dreawer.favorite.constants.ServiceConstants.*;
import static com.dreawer.favorite.constants.DAOConstants.*;

/**
 * <CODE>FavoriteService</CODE> 收藏信息服务。
 *
 * @author zengzhijie
 * @version 1.0
 * @since Favorite 1.0
 */
@Service(FAVORITE_SERVICE)
public class FavoriteService {

    @Resource(name = FAVORITE_DAO)
    private FavoriteDao favoriteDao; // 收藏信息DAO

    /**
     * 添加收藏。
     *
     * @param favorite 收藏信息。
     * @author zengzhijie
     * @since 1.0
     */
    public void add(Favorite favorite) {
        favoriteDao.save(favorite);
    }

    /**
     * 更新收藏信息状态(正常或移除)。
     *
     * @param favorite 收藏信息。
     * @return 成功更新的记录数。
     * @author zengzhijie
     * @since 1.0
     */
    public int updateFavorite(Favorite favorite) {
        return favoriteDao.updateFavorite(favorite);
    }

    /**
     * 获取收藏信息。
     *
     * @param id 收藏内容id。
     * @return 收藏信息。如果存在返回收藏信息，否则返回null。
     * @author zengzhijie
     * @since 1.0
     */
    public Favorite getFavorite(String id) {
        return favoriteDao.findFavorite(id);
    }

    /**
     * 获取指定用户针对指定内容的指定收藏信息。
     *
     * @param collectorId 收藏者。
     * @param contentId   被收藏内容。
     * @return 收藏信息。如果存在返回收藏信息，否则返回null。
     * @author zengzhijie
     * @since 1.0
     */
    public Favorite getFavorite(String collectorId, String contentId) {
        return favoriteDao.findFavorite(collectorId, contentId);
    }

    /**
     * 获取指定收藏者的收藏信息。
     *
     * @param collectorId 收藏者。
     * @param pagesize  分页最大记录数。
     * @return 收藏列表。如果存在返回收藏列表，否则返回长度为0的收藏列表。
     * @author zengzhijie
     * @since 1.0
     */
    public List<Favorite> getFavorites(String collectorId, Integer pagesize) {
        return getFavorites(collectorId, null, pagesize);
    }

    /**
     * 获取指定收藏者的收藏信息。
     *
     * @param collectorId 收藏者。
     * @param startTime 起始时间。
     * @param pagesize  分页最大记录数。
     * @return 收藏列表。如果存在返回收藏列表，否则返回长度为0的收藏列表。
     * @author zengzhijie
     * @since 1.0
     */
    public List<Favorite> getFavorites(String collectorId, Timestamp startTime, Integer pagesize) {
        return favoriteDao.findFavorites(collectorId, startTime, pagesize);
    }

    /**
     * 获取指定收藏者的收藏信息。
     *
     * @param collectorId 收藏者。
     * @param startTime 起始时间。
     * @return 收藏列表。如果存在返回收藏列表，否则返回长度为0的收藏列表。
     * @author zengzhijie
     * @since 1.0
     */
    public List<Favorite> getFavorites(String collectorId, Timestamp startTime) {
        return getFavorites(collectorId, startTime, null);
    }

}
