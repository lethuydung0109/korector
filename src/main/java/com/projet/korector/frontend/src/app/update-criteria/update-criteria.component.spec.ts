import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCriteriaComponent } from './update-criteria.component';

describe('UpdateCriteriaComponent', () => {
  let component: UpdateCriteriaComponent;
  let fixture: ComponentFixture<UpdateCriteriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCriteriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCriteriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
