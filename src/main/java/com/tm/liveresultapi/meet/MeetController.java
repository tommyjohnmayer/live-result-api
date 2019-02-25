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

@CrossOrigin(origins="*")
@RepositoryRestController
public class MeetController {
	
	@Autowired
	RawMeetRepository repo;

    private PagedResourcesAssembler<Meet> pagedAssembler;

    @Autowired
    public MeetController(PagedResourcesAssembler<Meet> pagedAssembler) {
        this.pagedAssembler = pagedAssembler;
    }

    private Page<Meet> getMeets(Pageable pageRequest){
    	Page<RawMeet> meets = repo.findAll(pageRequest);
        return meets.map(x -> new Meet(x));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path="/meets", produces = "application/hal+json")
    public ResponseEntity<Page<RawMeet>> getMeetsHal(Pageable pageRequest, PersistentEntityResourceAssembler assembler){
        return new ResponseEntity(pagedAssembler.toResource(getMeets(pageRequest), (ResourceAssembler) assembler), HttpStatus.OK);
    }
}
