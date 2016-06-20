package im.nll.data.fluent;

import com.google.common.hash.Hashing;
import org.apache.commons.codec.Charsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/5/12 下午6:45
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String salt = UUID.randomUUID().toString();
            String code = readDataFromConsole("输入验证码:");
            System.out.println(code);
            String password = "abc123xxx";
            String fixedSalt = "xfq";
            String result = Hashing.sha256().newHasher().putString(salt + password + fixedSalt, Charsets.UTF_8).hash().toString();
            System.out.println(result);
        }
    }

    private static String readDataFromConsole(String prompt) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            System.out.print(prompt);
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
