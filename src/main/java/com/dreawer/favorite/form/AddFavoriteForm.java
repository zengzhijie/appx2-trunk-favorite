package com.dreawer.favorite.form;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import static com.dreawer.favorite.constants.MessageConstants.*;
public class AddFavoriteForm {

    @NotEmpty(message = VAL_OBJECT_ID_NOT_EMPTY)
    private String objectId = null; // 被收藏对象ID号

    @NotEmpty(message = VAL_OBJECT_CATEGORY_NOT_EMPTY)
    private String category = null; // 被收藏对象分类

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

    /**
     * 获取属性 <TT>objectId</TT>（被收藏对象ID号）的值。
     *
     * @return <TT>objectId</TT> 被收藏对象ID号。
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置属性 <TT>objectId</TT>（被收藏对象ID号）的值。
     *
     * @param objectId 被收藏对象ID号。
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * 获取属性 <TT>category</TT>（被收藏对象分类）的值。
     *
     * @return <TT>category</TT> 被收藏对象分类。
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置属性 <TT>category</TT>（被收藏对象分类）的值。
     *
     * @param category 被收藏对象分类。
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
