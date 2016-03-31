$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$(function () {
    (function () {

        (function () {
            $('#popupYesNoBasic').click(function () {
                Lobibox.confirm({
                    msg: "Are you sure you want to delete this user?",
                });
            });
            $('#popupErrorBasic').click(function () {
                Lobibox.alert('error', {
                    msg: "Lorem ipsum dolor sit amet byron frown tumult minstrel wicked clouded bows columbine full"
                });
            });
            $('#popupSuccessBasic').click(function () {
                Lobibox.alert('success', {
                    msg: "Lorem ipsum dolor sit amet byron frown tumult minstrel wicked clouded bows columbine full"
                });
            });
            $('#popupInfoBasic').click(function () {
                Lobibox.alert('info', {
                    msg: "Lorem ipsum dolor sit amet byron frown tumult minstrel wicked clouded bows columbine full"
                });
            });
            $('#popupWarningBasic').click(function () {
                Lobibox.alert('warning', {
                    msg: "Lorem ipsum dolor sit amet byron frown tumult minstrel wicked clouded bows columbine full"
                });
            });
            $('#popupPromptBasic').click(function () {
                Lobibox.prompt('text', {
                    title: 'Please enter username',
                    attrs: {
                        placeholder: "Username"
                    }
                });
            });
            $('#popupProgressBasic').click(function () {
                var inter;
                Lobibox.progress({
                    title: 'Please wait',
                    label: 'Uploading files...',
                    onShow: function ($this) {
                        var i = 0;
                        inter = setInterval(function () {
                            window.console.log(i);
                            if (i > 100) {
                                inter = clearInterval(inter);
                            }
                            i = i + 0.1;
                            $this.setProgress(i);
                        }, 10);
                    },
                    closed: function () {
                        inter = clearInterval(inter);
                    }
                });
            });
            $('#popupProgressBootstrap').click(function () {
                var inter;
                Lobibox.progress({
                    title: 'Please wait',
                    label: 'Uploading files...',
                    progressTpl: '<div class="progress lobibox-progress-outer">\n\
                    <div class="progress-bar progress-bar-danger progress-bar-striped lobibox-progress-element" data-role="progress-text" role="progressbar"></div>\n\
                    </div>',
                    progressCompleted: function () {
                        Lobibox.notify('success', {
                            msg: 'Files were successfully uploaded'
                        });
                    },
                    onShow: function ($this) {
                        var i = 0;
                        inter = setInterval(function () {
                            window.console.log(i);
                            if (i > 100) {
                                clearInterval(inter);
                            }
                            i = i + 0.2;
                            $this.setProgress(i);
                        }, 1000 / 30);
                    },
                    closed: function () {
                        inter = clearInterval(inter);
                    }
                });
            });
            $('#popupWindowBasic').click(function () {
                Lobibox.window({
                    title: 'Window title',
                    content: [
                        '<p>Lorem ipsum dolor sit amet byron frown tumult minstrel wicked clouded bows columbine full. Panther nascetur estimation, croaked translations brood sharply federal basket. Yet virtues replies pans croaked org feelest, redden chicadeedee wipe, columbine humanity flood mood. Stayed frown ponderous shares bubbles skilled mood federal, shamed robe roll feathered life. Notifies life bows joys bubbles, clouded frown. Skilled wished sportive moved, shamed, year frown sank, universe, wove within. Infirm dames croaked sharply estimation wipe ponderous climb, shamed once basket oracle, smite frown stayed. Sharply bows basket minstrel skilled virtues, panther life. Dames notifies laid, willow listened frankincense croaked potenti. Minstrel since rowed frown, wipe shares, dames wished heaving potenti estimation panther columbine mighty flood.</p>',
                        '<p>Lorem ipsum dolor sit amet byron frown tumult minstrel wicked clouded bows columbine full. Panther nascetur estimation, croaked translations brood sharply federal basket. Yet virtues replies pans croaked org feelest, redden chicadeedee wipe, columbine humanity flood mood. Stayed frown ponderous shares bubbles skilled mood federal, shamed robe roll feathered life. Notifies life bows joys bubbles, clouded frown. Skilled wished sportive moved, shamed, year frown sank, universe, wove within. Infirm dames croaked sharply estimation wipe ponderous climb, shamed once basket oracle, smite frown stayed. Sharply bows basket minstrel skilled virtues, panther life. Dames notifies laid, willow listened frankincense croaked potenti. Minstrel since rowed frown, wipe shares, dames wished heaving potenti estimation panther columbine mighty flood.</p>',
                        '<p>Lorem ipsum dolor sit amet byron frown tumult minstrel wicked clouded bows columbine full. Panther nascetur estimation, croaked translations brood sharply federal basket. Yet virtues replies pans croaked org feelest, redden chicadeedee wipe, columbine humanity flood mood. Stayed frown ponderous shares bubbles skilled mood federal, shamed robe roll feathered life. Notifies life bows joys bubbles, clouded frown. Skilled wished sportive moved, shamed, year frown sank, universe, wove within. Infirm dames croaked sharply estimation wipe ponderous climb, shamed once basket oracle, smite frown stayed. Sharply bows basket minstrel skilled virtues, panther life. Dames notifies laid, willow listened frankincense croaked potenti. Minstrel since rowed frown, wipe shares, dames wished heaving potenti estimation panther columbine mighty flood.</p>'
                    ].join("")
                });
            });
        })();
    })();

});