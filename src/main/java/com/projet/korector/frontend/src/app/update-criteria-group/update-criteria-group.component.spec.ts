import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCriteriaGroupComponent } from './update-criteria-group.component';

describe('UpdateCriteriaGroupComponent', () => {
  let component: UpdateCriteriaGroupComponent;
  let fixture: ComponentFixture<UpdateCriteriaGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCriteriaGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCriteriaGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
