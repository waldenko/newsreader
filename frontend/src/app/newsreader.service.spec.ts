import { TestBed, inject } from '@angular/core/testing';

import { NewsreaderService } from './newsreader.service';

describe('NewsreaderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NewsreaderService]
    });
  });

  it('should be created', inject([NewsreaderService], (service: NewsreaderService) => {
    expect(service).toBeTruthy();
  }));
});
