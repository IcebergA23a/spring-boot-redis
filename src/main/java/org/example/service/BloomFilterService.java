package org.example.service;

import org.example.request.BloomFilterRequest;

public interface BloomFilterService {

    Boolean addToBloomFilter(BloomFilterRequest request);

    Boolean mightContain(String key, String value);

    long bloomFilterCount(String key);
}
