package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/graph-branch")
public class GraphBranchController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public GraphBranch[] getGraphBranches(@RequestParam String owner, @RequestParam String repo) {
        String url = "http://localhost:3000/graph-branch/all?owner=" + owner + "&repo=" + repo;
        GraphBranch[] branches = restTemplate.getForObject(url, GraphBranch[].class);
//        if (branches != null) {
//            for (GraphBranch branch : branches) {
//                System.out.println(branch);
//            }
//        }
        return branches;
    }
}
