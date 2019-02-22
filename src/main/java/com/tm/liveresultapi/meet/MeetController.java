package com.tm.liveresultapi.meet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RequestMapping(path="/meets")
@RepositoryRestController
@RestController
public class MeetController {
	
	@Autowired
	RawMeetRepository repo;

    private PagedResourcesAssembler<RawMeet> pagedAssembler;

    @Autowired
    public MeetController(PagedResourcesAssembler<RawMeet> pagedAssembler) {
        this.pagedAssembler = pagedAssembler;
    }

    private Page<RawMeet> getMeets(Pageable pageRequest){
        return repo.findAll(pageRequest);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(produces = "application/hal+json")
    public ResponseEntity<Page<RawMeet>> getMeetsHal(Pageable pageRequest, PersistentEntityResourceAssembler assembler){
        return new ResponseEntity(pagedAssembler.toResource(getMeets(pageRequest), (ResourceAssembler) assembler), HttpStatus.OK);
    }
}
