package com.banana;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;

public class ExampleValueProfileFuzzer {
    // consume data from fuzzer and give it to function under test
    public static void fuzzerTestOneInput(FuzzedDataProvider data) {
        String str = data.consumeString(100); // get a string with max_len 100
        long input1 = data.consumeLong( );
        long input2 = data.consumeLong( );
        // call function under test with fuzz data
        ApplicationUnderTest.consume(str, input1, input2);
    }
}
