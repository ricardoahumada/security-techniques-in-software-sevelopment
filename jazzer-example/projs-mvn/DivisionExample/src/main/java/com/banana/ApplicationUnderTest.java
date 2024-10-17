class ApplicationUnderTest {
    private static String base64Encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    // insecure encrypt with xor and static key
    private static long insecureEncrypt(long input) {
        long key = 0xefe4eb93215cb6b0L;
        return input ^ key;
    }

    public static void consume(String str, long input1, long input2) {
        if (base64Encode(str).equals("SmF6emVy")) { // base64Encode("Jazzer")
            // To get here, the fuzzer has to guess the expected input byte by byte with value profile
            if (insecureEncrypt(input1) == 0x9fc48ee64d3dc090L) { // insecureEncrypt(Long.toString("value"))
                // To get here, the fuzzer has to guess the expected input bitwise with value profile
                if (insecureEncrypt(input2) == 0x888a82ff483ad9c2L) { // insecureEncrypt(Long.toString("profile"))
                    // To get here, the fuzzer needs fake program counters and value profile
                    throw new FuzzerSecurityIssue("You found a Bug");
                }
            }
        }
    }
}