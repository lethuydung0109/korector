import { TestBed } from '@angular/core/testing';

import { SessionCritereService } from './session-critere.service';

describe('SessionCritereService', () => {
  let service: SessionCritereService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionCritereService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
