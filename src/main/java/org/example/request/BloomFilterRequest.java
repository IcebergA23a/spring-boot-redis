package org.example.request;

/**
 * @Desc TODO
 * @Author bingshan
 * @Date 2025/12/12 14:46
 */
public class BloomFilterRequest {

    private String key;
    private String[] value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }
}
