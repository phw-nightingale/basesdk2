package xyz.frt.basesdk2.controller;

import org.springframework.web.bind.annotation.*;
import xyz.frt.basesdk2.common.GraphDTO;
import xyz.frt.basesdk2.common.JsonResult;
import xyz.frt.basesdk2.common.graph.Position;
import xyz.frt.basesdk2.service.GuideService;

@RestController
@RequestMapping("/guide")
public class GuideController {

    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @PostMapping("/graph")
    public JsonResult createGraph(@RequestBody GuideParams params) {
        return JsonResult.success("创建成功", guideService.createGuide(params.getPs(), params.getPs().length, params.getMaxVerNum()));
    }

    @PostMapping("/positions")
    public JsonResult insertPosition(@RequestParam Position p) {
        return JsonResult.success("添加成功", guideService.insertPosition(p));
    }

    @DeleteMapping("/positions")
    public JsonResult deletePosition(@RequestParam Position p) {
        return JsonResult.success("删除成功", guideService.deletePosition(p));
    }

    @PostMapping("/edges")
    public JsonResult insertEdge(
            @RequestParam Integer v1,
            @RequestParam Integer v2,
            @RequestParam Integer weight) {
        return JsonResult.success("插入成功", guideService.insertEdge(v1, v2, weight));
    }

    @DeleteMapping("/edges")
    public JsonResult deleteEdge(
            @RequestParam Integer v1,
            @RequestParam Integer v2
    ) {
        return JsonResult.success("删除成功", guideService.deleteEdge(v1, v2));
    }

    @GetMapping("/graph")
    public JsonResult getGraph() {
        return JsonResult.success("获取成功", new GraphDTO<>(guideService.getPositions(), guideService.getArcs()));
    }

    @GetMapping("/dist/{v0}")
    public JsonResult getShortestDist(@PathVariable Integer v0) {
        return JsonResult.success("获取成功", guideService.getShortestDistDij(v0));
    }

    @GetMapping("/dist/{v0}/{v1}")
    public JsonResult getShortestDist(@PathVariable Integer v0, @PathVariable Integer v1) {
        return JsonResult.success("获取成功", guideService.getShortestDistDij(v0).getLength(v1));
    }

    @GetMapping("/line/{v0}/{v1}")
    public JsonResult getShortestLine(@PathVariable Integer v0, @PathVariable Integer v1) {
        return JsonResult.success("获取成功", guideService.getShortestDistDij(v0).getLine(v1));
    }
}
