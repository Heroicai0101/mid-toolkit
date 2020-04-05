package com.cgx.middleware.extension.model;

import lombok.*;

/**
 * 扩展点坐标: 注意查找注入扩展点是根据 hash(bizCode, extensionPoint) 来定位
 */
@EqualsAndHashCode(of = {"bizCode", "extensionPoint"})
@Builder
@Getter
@Setter
@ToString
public class ExtensionCoordinate {

    /** 类比Maven的groupId */
    private String bizCode;

    /** 类比Maven的artifactId */
    private String extensionPoint;

    private String name;

    private String description;

    public static ExtensionCoordinate of(@NonNull String bizCode, @NonNull String extensionPoint) {
        return ExtensionCoordinate.builder()
                                  .bizCode(bizCode)
                                  .extensionPoint(extensionPoint)
                                  .build();
    }

}
