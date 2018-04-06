package com.sf.bcsp.base.export;

import com.sf.bcsp.data.base.BcspDataReader;

import java.util.Map;

public interface Export {
    void export(String fullName, String outFullName, Map<String,Object> baseParams, BcspDataReader bcspDataReader);
}
