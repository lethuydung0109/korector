import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CriteriaGroupListComponent } from './criteria-group-list.component';

describe('CriteriaGroupListComponent', () => {
  let component: CriteriaGroupListComponent;
  let fixture: ComponentFixture<CriteriaGroupListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CriteriaGroupListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CriteriaGroupListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
