package com.dreawer.favorite.controller;

import static com.dreawer.favorite.constants.ControllerConstants.*;
import static com.dreawer.favorite.constants.MessageConstants.*;
import static com.dreawer.favorite.constants.ServiceConstants.*;
import static com.dreawer.favorite.constants.DomainConstants.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dreawer.responsecode.rcdt.*;
import com.dreawer.responsecode.rcdt.Error;
import io.swagger.annotations.ApiOperation;
import javassist.CtMethod;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.dreawer.favorite.domain.Favorite;
import com.dreawer.favorite.domain.Favorites;
import com.dreawer.favorite.form.AddFavoriteForm;
import com.dreawer.favorite.form.AddFavoritesForm;
import com.dreawer.favorite.form.UpdateFavoritesForm;
import com.dreawer.favorite.lang.ContentStatus;
import com.dreawer.favorite.service.FavoriteService;
import com.dreawer.favorite.service.FavoritesService;
import com.dreawer.favorite.view.ViewFavoriteContent;
import com.dreawer.favorite.view.ViewFavorites;
import com.dreawer.favorite.controller.BaseController;

/**
 * <CODE>FavoriteController</CODE> 收藏业务控制器。
 *
 * @author zengzhijie
 * @version 1.0
 * @since Favorite 1.0
 */
@Controller(FAVORITE_CONTROLLER)
public class FavoriteController extends BaseController {

    @Resource(name = FAVORITE_SERVICE)
    private FavoriteService favoriteService; // 收藏信息服务

    @Resource(name = FAVORITES_SERVICE)
    private FavoritesService favoritesService; // 收藏夹信息服务

    private Logger logger = Logger.getLogger(this.getClass()); // 日志记录器


    /**
     * 功能描述: 添加收藏夹。
     *
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @ApiOperation(value = "添加收藏夹", notes = "根据form表单来指定添加收藏夹的信息")
    @RequestMapping(value = REQ_FAVORITES_ADD, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode addFavorites(HttpServletRequest req,
                              @RequestBody @Valid AddFavoritesForm form, BindingResult result) {

        try {
            if (result.hasErrors()) {
                return checkErrors(result);
            }
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            Favorites oldFavorites = favoritesService.getFavoritesByName(form.getName(), collectorId);
            if (oldFavorites != null) {
                return Error.APPSERVER;
            }
            Favorites favorites = new Favorites();
            favorites.setName(form.getName());
            favorites.setCollectorId(collectorId);
            favorites.setCreateTime(getNow());
            favoritesService.add(favorites);
            return Success.SUCCESS(favorites);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }


    /**
     * 功能描述: 删除收藏夹。
     *
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */
    @ApiOperation(value = "删除收藏夹", notes = "根据收藏夹id来指定删除")
    @RequestMapping(value = REQ_FAVORITES_DELETE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode deleteFavorites(HttpServletRequest req,
                                 @RequestBody @Valid UpdateFavoritesForm form, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return checkErrors(result);
            }
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            Favorites favorites = favoritesService.getFavoritesByName(form.getName(), collectorId);
            if (favorites == null) {
                return Error.DB(VAL__FAVORITES_NOTFOUND);
            }
            favoritesService.delete(favorites.getId());
            return Success.SUCCESS(favorites);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }

    /**
     * 功能描述: 更新收藏夹。
     *
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */
    @ApiOperation(value = "更新收藏夹", notes = "根据form表单来更新收藏夹的信息")
    @RequestMapping(value = REQ_FAVORITES_EDIT, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode editFavorites(HttpServletRequest req,
                               @RequestBody @Valid UpdateFavoritesForm form, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return checkErrors(result);
            }
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            Favorites favorites = favoritesService.getFavoritesById(form.getId());

            //判断该收藏夹名称是否存在
            Favorites favorites2 = favoritesService.getFavoritesByName(form.getName(), collectorId);
            if (favorites == null || favorites2 != null) {
                return Error.APPSERVER;
            }
            favorites.setName(form.getName());
            favorites.setCollectorId(collectorId);
            favorites.setUpdateTime(getNow());
            favoritesService.update(favorites);
            return Success.SUCCESS(favorites);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }


    /**
     * 功能描述: 获取收藏夹列表。
     *
     * @param: [req]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */
    @ApiOperation(value = "获取收藏夹列表", notes = "根据url获取收藏夹列表")
    @RequestMapping(value = REQ_FAVORITES_LIST, method = RequestMethod.GET)
    public @ResponseBody
    ResponseCode getFavoritesList(HttpServletRequest req) {
        try {
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            List<Favorites> favoritesList = favoritesService.getFavoritesList(collectorId);
            List<Favorite> favoriteList = favoriteService.getFavorites(collectorId, getNow());
            List<ViewFavorites> viewFavoritesList = new ArrayList<ViewFavorites>();
            for (Favorites favorites : favoritesList) {
                ViewFavorites viewFavorites = new ViewFavorites();
                String name = favorites.getName();
                viewFavorites.setId(favorites.getId());
                viewFavorites.setName(name);
                List<Favorite> list = new ArrayList<>();
                for (Favorite favorite : favoriteList) {
                    if (favorite.getContentCategory().equals(name)) {
                        list.add(favorite);
                    }
                }
                viewFavorites.setFavoriteList(list);
                viewFavoritesList.add(viewFavorites);
            }
            return Success.SUCCESS(viewFavoritesList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }

    /**
     * 功能描述: 收藏内容。
     *
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */
    @ApiOperation(value = "收藏内容", notes = "根据form表单来判断是添加或更新")
    @RequestMapping(value = REQ_FAVORITE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode favorite(HttpServletRequest req,
                          @RequestBody @Valid AddFavoriteForm form,
                          BindingResult result) {
        try {
            if (result.hasErrors()) {
                return checkErrors(result);
            }
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            String contentId = form.getObjectId();
            String contentCategory = form.getCategory();
            // 添加收藏
            Favorite favorite = favoriteService.getFavorite(collectorId, contentId);
            if (favorite == null) {
                favorite = new Favorite();
                favorite.setCollectorId(collectorId);
                favorite.setContentId(contentId);
                favorite.setContentCategory(contentCategory);
                favoriteService.add(favorite);
            } else if (favorite.getStatus() == ContentStatus.DELETED) {
                favorite.setStatus(ContentStatus.DEFAULT);
                favorite.setCollectorId(collectorId);
                favorite.setContentId(contentId);
                favorite.setContentCategory(contentCategory);
                favoriteService.updateFavorite(favorite);
            }
            return Success.SUCCESS(favorite);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }

    /**
     * 功能描述: 取消收藏。
     *
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */
    @ApiOperation(value = "取消收藏", notes = "根据form表单来取消收藏")
    @RequestMapping(value = REQ_UNFAVORITE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode unfavorite(HttpServletRequest req,
                            @RequestBody @Valid AddFavoriteForm form, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return checkErrors(result);
            }
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            // 验证内容是否合规
            String contentId = form.getObjectId();
            String contentCategory = form.getCategory();
            // 取消收藏
            Favorite favorite = favoriteService.getFavorite(collectorId, contentId);
            if (favorite != null && favorite.getStatus() == ContentStatus.DEFAULT) {
                favorite.setStatus(ContentStatus.DELETED);
                favorite.setCollectorId(collectorId);
                favorite.setContentId(contentId);
                favorite.setContentCategory(contentCategory);
                favorite.setUpdateTime(getNow());
                favoriteService.updateFavorite(favorite);
            }
            return Success.SUCCESS(favorite);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }


    /**
     * 功能描述: 获取指定收藏者的收藏信息。
     *
     * @param: [req]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */
    @ApiOperation(value = "获取指定收藏者的收藏信息", notes = "根据url获取指定收藏者的收藏信息")
    @RequestMapping(value = REQ_GET_USERFAVORITES, method = RequestMethod.GET)
    public @ResponseBody
    ResponseCode getUserFavorites(HttpServletRequest req) {

        try {
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            // 查询用户收藏
            List<Favorite> favorites = new ArrayList<>();
            if (req.getParameter("pageSize") != null) {
                Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));
                favorites = favoriteService.getFavorites(collectorId, getNow(), pageSize);
            } else {
                favorites = favoriteService.getFavorites(collectorId, getNow());
            }
            List<ViewFavoriteContent> viewFavoriteContents = new ArrayList<ViewFavoriteContent>();
            for (Favorite favorite : favorites) {
                ViewFavoriteContent viewFavorite = new ViewFavoriteContent();
                viewFavorite.setContentId(favorite.getContentId());
                viewFavorite.setCategory(favorite.getContentCategory());
                viewFavoriteContents.add(viewFavorite);
            }
            return Success.SUCCESS(viewFavoriteContents);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }

    /**
     * 功能描述: 更新收藏（收藏分类）。
     *
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */
    @ApiOperation(value = "更新收藏", notes = "根据form表单更新收藏")
    @RequestMapping(value = REQ_UPDATE_FAVORITE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode updateFavorite(HttpServletRequest req,
                                @RequestBody @Valid AddFavoriteForm form, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return checkErrors(result);
            }
            //根据网关用户id查询用户
            String collectorId = req.getHeader("userid");
            // 验证内容是否合规
            String contentId = form.getObjectId();
            String contentCategory = form.getCategory();
            // 更新收藏
            Favorite favorite = favoriteService.getFavorite(collectorId, contentId);
            if (favorite != null && favorite.getStatus() == ContentStatus.DEFAULT) {
                favorite.setContentId(contentId);
                favorite.setContentCategory(contentCategory);
                favorite.setCollectorId(collectorId);
                favorite.setUpdateTime(getNow());
                favoriteService.updateFavorite(favorite);
            }
            return Success.SUCCESS(favorite);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            // 返回失败标志及信息
            return Error.APPSERVER;
        }
    }
}
