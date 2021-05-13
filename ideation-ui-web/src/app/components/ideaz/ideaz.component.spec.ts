import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IdeazComponent } from './ideaz.component';

describe('IdeazComponent', () => {
  let component: IdeazComponent;
  let fixture: ComponentFixture<IdeazComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IdeazComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IdeazComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
