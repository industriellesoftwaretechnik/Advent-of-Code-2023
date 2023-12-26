package de.aemik.adventofcode;


import java.util.*;

public class Day19_Part1 {

    public static void main(String[] args) throws Exception {
        //new Day19_Part1().start("day19_example.txt");
        new Day19_Part1().start("day19_part1.txt");
    }

    Map<String, Workflow> workflows = new LinkedHashMap<>();
    List<Map<String, Part>> partsList = new ArrayList<>();

    public void start(String filename) throws Exception {
        final var lines = FileUtil.getLines(filename);
        loadData(lines);

    }

    private void loadData(List<String> lines) {
        boolean isParts = false;
        for (String line : lines) {
            if (line.isEmpty()) {
                isParts = true;
                continue;
            }

            if (!isParts) {
                // px{a<2006:qkq,m>2090:A,rfg}
                final var workflow = createWorkflow(line);
                workflows.put(workflow.id(), workflow);
            } else {
                // {x=787,m=2655,a=1222,s=2876}
                var splits = line.replace("{x=", "")
                        .replace("m=", "")
                        .replace("a=", "")
                        .replace("s=", "")
                        .replace("}", "")
                        .split(",");
                partsList.add(createPartMap(splits));
            }
        }

        workflows.values().forEach(System.out::println);
        partsList.forEach(System.out::println);
        System.out.println();

        int result = 0;
        for (Map<String, Part> partMap : partsList) {
            final boolean accept = calculate("in", partMap);
            if (accept) {
                result = result + partMap.values().stream().map(x -> x.number).reduce(0, Integer::sum);
            }
        }

        System.out.println(result);
    }

    private boolean calculate(String startId, Map<String, Part> partMap) {
        if (startId.equals("A")) {
            return true;
        } else if (startId.equals("R")) {
            return false;
        }

        System.out.println("");
        final var workflow = workflows.get(startId);
        System.out.println(partMap);
        System.out.println(workflow);

        for (Rule rule : workflow.rules) {
            System.out.println(rule);

            boolean pass = false;
            if (rule.compareType.equals(">")) {
                pass = partMap.get(rule.part.id).number() > rule.part().number();
            } else {
                pass = partMap.get(rule.part.id).number() < rule.part().number();
            }
            if (pass) {
                return calculate(rule.targetId(), partMap);
            }
        }

        return calculate(workflow.defaultTargetId(), partMap);
    }

    private Workflow createWorkflow(String line) {
        // px{a<2006:qkq,m>2090:A,rfg}
        var keyAndWorklowSplit = line.replace("}", "").split("\\{");
        var id = keyAndWorklowSplit[0];

        List<Rule> rules = new ArrayList<>();
        String defaultTargetId = null;
        var rulesString = keyAndWorklowSplit[1].split(",");
        for (int i = 0; i < rulesString.length; i++) {
            if (i == rulesString.length-1) {
                defaultTargetId = rulesString[i];
            } else {
                var ruleAndTarget = rulesString[i].split(":");
                final var ruleSplit = ruleAndTarget[0].split(">|<");
                rules.add(new Rule(
                        new Part(ruleSplit[0],
                                Integer.parseInt(ruleSplit[1])), ruleAndTarget[0].contains(">") ? ">" : "<", ruleAndTarget[1]));
            }
        }

        return new Workflow(id, rules, defaultTargetId);
    }

    private Map<String, Part> createPartMap(String[] partArray) {
        Map<String, Part> partMap = new HashMap<>();
        partMap.put("x", new Part("x", Integer.parseInt(partArray[0])));
        partMap.put("m", new Part("m", Integer.parseInt(partArray[1])));
        partMap.put("a", new Part("a", Integer.parseInt(partArray[2])));
        partMap.put("s", new Part("s", Integer.parseInt(partArray[3])));
        return partMap;
    }

    record Workflow(String id, List<Rule> rules, String defaultTargetId) {}

    record Rule(Part part, String compareType, String targetId) {}

    // record RuleTarget(RuleTargetType type, String worflowId) {}

    enum RuleTargetType {
        A, R, ID
    }

    record Part(String id, int number) {}
}