-- ==================================================
-- 收藏信息表
-- ==================================================
DROP TABLE IF EXISTS favi_favorite;
CREATE TABLE favi_favorite (
	id 			CHAR(32) NOT NULL COMMENT 'ID序列号',
	colr_id 	CHAR(32) NOT NULL COMMENT '收藏者用户ID号',
	colr_idt_id CHAR(32) NOT NULL COMMENT '收藏者身份ID号',
	cont_id 	CHAR(32) NOT NULL COMMENT '被收藏内容的ID号',
	cont_ctg 	VARCHAR(64) NOT NULL COMMENT '被收藏内容的分类',
	favs_id		CHAR(32) DEFAULT NULL COMMENT '收藏夹ID',
	status 		CHAR(32) NOT NULL DEFAULT 'DEFAULT' COMMENT '状态（DEFAULT-正常、DELETED-已删除）',
	cre_tim 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
	updr_id 	CHAR(32) COMMENT '更新者用户ID号',
	updr_idt_id CHAR(32) COMMENT '更新者身份ID号',
	upd_tim 	DATETIME COMMENT '更新时间',
	remark 		VARCHAR(128) COMMENT '备注',
	PRIMARY KEY (id)
) COMMENT='收藏信息表' ENGINE=InnoDB;
CREATE UNIQUE INDEX uniq_idx_favorite_colr_idt_id ON favi_favorite(colr_idt_id, cont_id);
CREATE INDEX idx_favorite_colr_idt_id ON favi_favorite(colr_idt_id);
CREATE INDEX idx_favorite_cont_id ON favi_favorite(cont_id);

-- ==================================================
-- 收藏夹信息表
-- ==================================================
DROP TABLE IF EXISTS favi_favorites;
CREATE TABLE favi_favorites (
	id 			CHAR(32) NOT NULL COMMENT 'ID序列号',
	colr_id 	CHAR(32) NOT NULL COMMENT '收藏者用户ID号',
	colr_idt_id CHAR(32) NOT NULL COMMENT '收藏者身份ID号',
	name 		VARCHAR(20) NOT NULL COMMENT '收藏夹名',
	cre_tim 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updr_id 	CHAR(32) COMMENT '更新者用户ID号',
	updr_idt_id CHAR(32) COMMENT '更新者身份ID号',
	upd_tim 	DATETIME COMMENT '更新时间',
	remark 		VARCHAR(128) COMMENT '备注',
	PRIMARY KEY (id)
) COMMENT='收藏夹信息表' ENGINE=InnoDB;
CREATE INDEX idx_favorites_colr_idt_id ON favi_favorites(colr_idt_id);
