package dass21.example.dass21; // Check your package name!

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class DassController {

    @Autowired private DassRepository repo;

    private final String[] questionTexts = {
            "I found it hard to wind down", "I was aware of dryness of my mouth",
            "I couldn't seem to experience any positive feeling at all",
            "I experienced breathing difficulty", "I found it difficult to work up the initiative to do things",
            "I tended to over-react to situations", "I experienced trembling (e.g. in the hands)",
            "I felt that I was using a lot of nervous energy", "I was worried about panic situations",
            "I felt that I had nothing to look forward to", "I found myself getting agitated",
            "I found it difficult to relax", "I felt down-hearted and blue",
            "I was intolerant of anything keeping me from getting on", "I felt I was close to panic",
            "I was unable to become enthusiastic about anything", "I felt I wasn't worth much as a person",
            "I felt that I was rather touchy", "I was aware of my heart action",
            "I felt scared without any good reason", "I felt that life was meaningless"
    };

    @GetMapping("/")
    public String welcomePage(Model m) {
        m.addAttribute("date", LocalDate.now());
        return "welcome";
    }

    @PostMapping("/start")
    public String startQuestions(@RequestParam String userName, Model m) {
        m.addAttribute("userName", userName);
        m.addAttribute("questions", questionTexts);
        return "questions";
    }

    @PostMapping("/submit")
    public String calculate(@RequestParam String userName, @RequestParam Map<String, String> params, Model m) {
        int d = sum(params, new int[]{3, 5, 10, 13, 16, 17, 21}) * 2;
        int a = sum(params, new int[]{2, 4, 7, 9, 15, 19, 20}) * 2;
        int s = sum(params, new int[]{1, 6, 8, 11, 12, 14, 18}) * 2;

        DassResult res = new DassResult();
        res.setUserName(userName);
        res.setTestDate(LocalDate.now());
        res.setDepScore(d); res.setAnxScore(a); res.setStrScore(s);
        res.setDepLevel(getLvl(d, 4, 6, 10, 13));
        res.setAnxLevel(getLvl(a, 3, 5, 7, 9));
        res.setStrLevel(getLvl(s, 7, 9, 12, 16));

        repo.save(res);
        m.addAttribute("res", res);
        return "result";
    }

    private int sum(Map<String, String> p, int[] ids) {
        int total = 0;
        for(int i : ids) total += Integer.parseInt(p.getOrDefault("q" + i, "0"));
        return total;
    }

    private String getLvl(int s, int n, int mi, int mo, int se) {
        if(s <= n) return "Normal"; if(s <= mi) return "Mild";
        if(s <= mo) return "Moderate"; if(s <= se) return "Severe"; return "Extremely Severe";
    }
}