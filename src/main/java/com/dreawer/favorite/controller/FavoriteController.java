package com.dreawer.favorite.controller;

import static com.dreawer.favorite.constants.ControllerConstants.*;
import static com.dreawer.favorite.constants.MessageConstants.*;
import static com.dreawer.favorite.constants.ServiceConstants.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dreawer.responsecode.rcdt.*;
import com.dreawer.responsecode.rcdt.Error;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.dreawer.dream.domain.Content;
//import com.dreawer.dream.view.Error;
//import com.dreawer.dream.view.JSONResponse;
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
import com.dreawer.sso.domain.User;

/**
 * <CODE>FavoriteController</CODE> 收藏业务控制器。
 *
 * @author Sdanly, David Dai
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
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_FAVORITES_ADD, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode addFavorites(HttpServletRequest req,
                              @Valid AddFavoritesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return checkErrors(result);
        }
            //用户登录
            User user = getSignInUser(req);

            Favorites oldFavorites = favoritesService.getFavoritesByName(form.getName(), user);
            if (oldFavorites != null) {
                //return new JSONResponse(false);
                return Error.DB(VAL__FAVORITES_EXISTS);
            }
            Favorites favorites = new Favorites();
            favorites.setName(form.getName());
            favorites.setCollector(user);
            favoritesService.add(favorites);
            //return new JSONResponse(true);
            return Success.SUCCESS(favorites);
    }


    /**
     * 功能描述: 删除收藏夹。
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_FAVORITES_DELETE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode deleteFavorites(HttpServletRequest req,
                                 @Valid UpdateFavoritesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return checkErrors(result);
        }
            //用户登录
            User user = getSignInUser(req);
            Favorites favorites = favoritesService.getFavoritesByName(form.getName(), user);
            if (favorites == null) {
                //return new JSONResponse(false);
                return Error.DB(VAL__FAVORITES_NOTFOUND);
            }
            favoritesService.delete(favorites.getId());
            //return new JSONResponse(true);
            return Success.SUCCESS(favorites);
    }

    /**
     * 功能描述: 更新收藏夹。
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_FAVORITES_EDIT, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode editFavorites(HttpServletRequest req,
                               @Valid UpdateFavoritesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return checkErrors(result);
        }
            //用户登录
            User user = getSignInUser(req);

            Favorites favorites = favoritesService.getFavoritesById(form.getId());
            //判断该收藏夹名称是否存在
            Favorites favorites2 = favoritesService.getFavoritesByName(form.getName(), user);
            /*if (favorites == null || favorites2 != null) {
                //return new JSONResponse(false);
                return Error.DB()
            }*/
            if(favorites==null){
                return Error.DB(VAL_FAVORITES_ID_NOT_EMPTY);
            }
            if(favorites2==null){
                return Error.DB(VAL__FAVORITES_EXISTS);
            }
            favorites.setName(form.getName());
            favorites.setCollector(user);
            favorites.setUpdateTime(getNow());
            favoritesService.update(favorites);
            //return new JSONResponse(true);
            return  Success.SUCCESS(favorites);
    }

    /**
     * 功能描述: 获取收藏夹列表。
     * @param: [req]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_FAVORITES_LIST, method = RequestMethod.GET)
    public @ResponseBody
    ResponseCode getFavoritesList(HttpServletRequest req) {

            //用户登录
            User user = getSignInUser(req);

            List<Favorites> favoritesList = favoritesService.getFavoritesList(user);
            List<Favorite> favoriteList = favoriteService.getFavorites(user, getNow());
            List<ViewFavorites> viewFavoritesList = new ArrayList<ViewFavorites>();
            for (Favorites favorites : favoritesList) {
                ViewFavorites viewFavorites = new ViewFavorites();
                String name = favorites.getName();
                viewFavorites.setId(favorites.getId());
                viewFavorites.setName(name);
                List<Favorite> list = new ArrayList<>();
                for (Favorite favorite : favoriteList) {
                    if (favorite.getContent().getCategory().equals(name)) {
                        list.add(favorite);
                    }
                }
                viewFavorites.setFavoriteList(list);
                viewFavoritesList.add(viewFavorites);
            }

            //return new JSONResponse(true, viewFavoritesList);
            return  Success.SUCCESS(viewFavoritesList);
    }

    /**
     * 功能描述: 收藏内容。
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_FAVORITE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode favorite(HttpServletRequest req,
                          @Valid AddFavoriteForm form, BindingResult result) {
        if (result.hasErrors()) {
            return checkErrors(result);
        }
            User user = getSignInUser(req);

            Content content = new Content(form.getObjectId());
            content.setCategory(form.getCategory());

            // 添加收藏
            Favorite favorite = favoriteService.getFavorite(user, content);
            if (favorite == null) {
                favorite = new Favorite();
                favorite.setCollector(user);
                favorite.setContent(content);
                favoriteService.add(favorite);
            } else if (favorite.getStatus() == ContentStatus.DELETED) {
                favorite.setStatus(ContentStatus.DEFAULT);
                favorite.setCollector(user);
                favorite.setContent(content);
                favoriteService.updateFavorite(favorite);
            }
            //return new JSONResponse(true);
            return Success.SUCCESS(favorite);

    }

    /**
     * 功能描述: 取消收藏。
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_UNFAVORITE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode unfavorite(HttpServletRequest req,
                            @Valid AddFavoriteForm form, BindingResult result) {
        if (result.hasErrors()) {
            return checkErrors(result);
        }

            User user = getSignInUser(req);

            // 验证内容是否合规
            Content content = new Content(form.getObjectId());
            content.setCategory(form.getCategory());

            // 取消收藏
            Favorite favorite = favoriteService.getFavorite(user, content);
            if (favorite != null && favorite.getStatus() == ContentStatus.DEFAULT) {
                favorite.setStatus(ContentStatus.DELETED);
                favorite.setCollector(user);
                favorite.setUpdateTime(getNow());
                favoriteService.updateFavorite(favorite);
            }
            //return new JSONResponse(true);
            return Success.SUCCESS(favorite);

    }


    /**
     * 功能描述: 获取指定收藏者的收藏信息。
     * @param: [req]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_GET_USERFAVORITES, method = RequestMethod.GET)
    public @ResponseBody
    ResponseCode getUserFavorites(HttpServletRequest req) {
            User user = getSignInUser(req);

            // 查询用户收藏
            List<Favorite> favorites = new ArrayList<>();
            if (req.getParameter("pageSize") != null) {
                Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));
                favorites = favoriteService.getFavorites(user, getNow(), pageSize);
            } else {
                favorites = favoriteService.getFavorites(user, getNow());
            }

            List<ViewFavoriteContent> viewFavoriteContents = new ArrayList<ViewFavoriteContent>();
            for (Favorite favorite : favorites) {
                ViewFavoriteContent viewFavorite = new ViewFavoriteContent();
                viewFavorite.setContentId(favorite.getContent().getId());
                viewFavorite.setCategory(favorite.getContent().getCategory());
                viewFavoriteContents.add(viewFavorite);
            }
            return Success.SUCCESS(viewFavoriteContents);

    }

    /**
     * 功能描述: 更新收藏（收藏分类）。
     * @param: [req, form, result]
     * @return: com.dreawer.responsecode.rcdt.ResponseCode
     * @auther: zengzhijie
     */

    @RequestMapping(value = REQ_UPDATE_FAVORITE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseCode updateFavorite(HttpServletRequest req,
                                @Valid AddFavoriteForm form, BindingResult result) {
        if (result.hasErrors()) {
            return checkErrors(result);
        }

            User user = getSignInUser(req);

            // 验证内容是否合规
            Content content = new Content(form.getObjectId());
            content.setCategory(form.getCategory());

            // 更新收藏
            Favorite favorite = favoriteService.getFavorite(user, content);
            if (favorite != null && favorite.getStatus() == ContentStatus.DEFAULT) {
                favorite.setContent(content);
                favorite.setCollector(user);
                favorite.setUpdateTime(getNow());
                favoriteService.updateFavorite(favorite);
            }
            return Success.SUCCESS(favorite);

    }
}
