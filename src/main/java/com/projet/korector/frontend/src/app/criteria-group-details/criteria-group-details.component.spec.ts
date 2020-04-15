import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CriteriaGroupDetailsComponent } from './criteria-group-details.component';

describe('CriteriaGroupDetailsComponent', () => {
  let component: CriteriaGroupDetailsComponent;
  let fixture: ComponentFixture<CriteriaGroupDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CriteriaGroupDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CriteriaGroupDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
