//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class JournalController {
//
//
//    private Map<Long, JournalEntry> map = new HashMap<>();
//
//    @PostMapping
//    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry){
//        return map.put(journalEntry.getId(), journalEntry);
//    }
//
//    @GetMapping("/all")
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(map.values());
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getById(@PathVariable Long myId){
//        return map.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public JournalEntry deleteById(@PathVariable Long myId){
//        return map.remove(myId);
//    }
//    @PutMapping("/id/{myId}")
//    public JournalEntry updateById(@PathVariable Long myId, @RequestBody JournalEntry journalEntry){
//        return map.put(myId, journalEntry);
//    }
//}
