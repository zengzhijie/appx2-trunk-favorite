package com.dreawer.favorite.service;
import static com.dreawer.favorite.constants.ServiceConstants.*;
import static com.dreawer.favorite.constants.DAOConstants.*;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dreawer.favorite.domain.Favorites;
import com.dreawer.favorite.persistence.FavoritesDao;

/**
 * <CODE>FavoritesService</CODE> 收藏信息服务。
 *
 * @author Sdanly
 * @version 1.0
 * @since Favorite 1.0
 */
@Service(FAVORITES_SERVICE)
public class FavoritesService {

    @Resource(name = FAVORITES_DAO)
    private FavoritesDao favoritesDao; // 收藏信息DAO

    /**
     * 添加收藏夹。
     *
     * @param favorites 收藏夹信息。
     * @author David Dai
     * @since 1.0
     */
    public void add(Favorites favorites) {
        favoritesDao.save(favorites);
    }

    /**
     * 删除收藏夹信息。
     *
     * @param id 收藏夹id。
     * @return 成功删除的记录数。
     * @author kael
     * @since 1.0
     */
    public int delete(String id) {
        return favoritesDao.delete(id);
    }

    /**
     * 更新收藏夹信息。
     *
     * @param favorites 收藏夹信息。
     * @return 成功更新的记录数。
     * @author kael
     * @since 1.0
     */
    public int update(Favorites favorites) {
        return favoritesDao.update(favorites);
    }

    /**
     * 获取收藏夹信息。
     *
     * @param collectorId 收藏夹内容id。
     * @return 收藏夹信息。如果存在返回收藏夹信息，否则返回null。
     * @author David Dai
     * @since 1.0
     */
    public List<Favorites> getFavoritesList(String collectorId) {
        return favoritesDao.findList(collectorId);
    }

    /**
     * 获取收藏夹信息。
     *
     * @param name 收藏夹名称。
     * @return 收藏夹信息。如果存在返回收藏夹信息，否则返回null。
     * @author David Dai
     * @since 1.0
     */
    public Favorites getFavoritesByName(String name, String collectorId) {
        return favoritesDao.findByName(name, collectorId);
    }

    /**
     * 获取收藏夹信息。
     *
     * @param id 收藏夹id。
     * @return 收藏夹信息。如果存在返回收藏夹信息，否则返回null。
     * @author David Dai
     * @since 1.0
     */
    public Favorites getFavoritesById(String id) {
        return favoritesDao.findById(id);
    }

}
