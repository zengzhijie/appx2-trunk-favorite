package com.dreawer.favorite.form;
import static com.dreawer.favorite.constants.MessageConstants.*;
import org.hibernate.validator.constraints.NotEmpty;

public class UpdateFavoritesForm extends AddFavoritesForm {

    @NotEmpty(message = VAL_FAVORITES_ID_NOT_EMPTY)
    private String id = null; // 收藏夹id

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

    /**
     * 获取属性 <TT>id</TT>（收藏夹id）的值。
     *
     * @return <TT>id</TT> 收藏夹id。
     */
    public String getId() {
        return id;
    }

    /**
     * 设置属性 <TT>id</TT>（收藏夹id）的值。
     *
     * @param id 收藏夹id。
     */
    public void setId(String id) {
        this.id = id;
    }

}
