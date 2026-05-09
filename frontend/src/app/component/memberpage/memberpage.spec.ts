import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberpageTs } from './memberpage.js';

describe('MemberpageTs', () => {
  let component: MemberpageTs;
  let fixture: ComponentFixture<MemberpageTs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MemberpageTs]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MemberpageTs);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
