import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CriteriaDetailsComponent } from './criteria-details.component';

describe('CriteriaDetailsComponent', () => {
  let component: CriteriaDetailsComponent;
  let fixture: ComponentFixture<CriteriaDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CriteriaDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CriteriaDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
