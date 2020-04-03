import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassesStatsComponent } from './classes-stats.component';

describe('ClassesStatsComponent', () => {
  let component: ClassesStatsComponent;
  let fixture: ComponentFixture<ClassesStatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassesStatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassesStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
