package com.banana;


import com.code_intelligence.jazzer.api.FuzzedDataProvider;

public class DivisionExampleFuzzer {
    public static void fuzzerTestOneInput(FuzzedDataProvider data) {
        int a = data.consumeInt();
        int b = data.consumeInt();
        DivisionExample.divide(a,b);
    }
}