package org.example.service;

public interface HyperLogLogService {

    /**
     * 添加元素到 HyperLogLog 结构
     * @param key
     * @param value
     * @return
     */
    Long pfAdd(String key, String value);

    /**
     * 获取 HyperLogLog 结构的基数估算值
     * @param key
     * @return
     */
    Long pfCount(String key);

    /**
     * 合并多个 HyperLogLog 结构到目标 HyperLogLog 结构
     * @param destKey
     * @param sourceKeys
     * @return
     */
    Long pfMerge(String destKey, String... sourceKeys);
}
