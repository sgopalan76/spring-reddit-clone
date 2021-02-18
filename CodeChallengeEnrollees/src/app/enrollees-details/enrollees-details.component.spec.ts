import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrolleesDetailsComponent } from './enrollees-details.component';

describe('EnrolleesDetailsComponent', () => {
  let component: EnrolleesDetailsComponent;
  let fixture: ComponentFixture<EnrolleesDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnrolleesDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrolleesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
