import { WePlanningAngularPage } from './app.po';

describe('we-planning-angular App', function() {
  let page: WePlanningAngularPage;

  beforeEach(() => {
    page = new WePlanningAngularPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
