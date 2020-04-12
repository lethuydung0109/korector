import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCriteriaGroupComponent } from './create-criteria-group.component';

describe('CreateCriteriaGroupComponent', () => {
  let component: CreateCriteriaGroupComponent;
  let fixture: ComponentFixture<CreateCriteriaGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateCriteriaGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCriteriaGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
