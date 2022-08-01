// Web-based Urdu Transliteration by Faried Nawaz is licensed under a
// Creative Commons Attribution-No Derivative Works 3.0 Unported License.
// For details about this license, see
// http://creativecommons.org/licenses/by-nd/3.0/

// - widget is the textarea or input tag you want to write urdu in
// - button is optional; it is something the user can press to toggle between
//   english and urdu.  see http://www.node.pk/urdu/ for an example
// - startenable is optional; if it's not null or false, the textarea will be
//   in urdu mode on startup
function bindUrdu(widget, button, startenable) {
    if (widget.urduBound) return;
    var URDU_MAP = {
        a: '\u0627', // alef
        A: '\u0622', // mad
        b: '\u0628',
        B: '\u0613',
        c: '\u0686',
        C: '\u062B',
        d: '\u062F',
        D: '\u0688',
        e: '\u0639',
        E: '\u0611',
        f: '\u0641',
        F: '\u060C', // comma
        g: '\u06AF',
        G: '\u063A',
        h: '\u0647',
        H: '\u062D',
        i: '\u06CC',
        I: '\u0670',
        j: '\u062C',
        J: '\u0636',
        k: '\u0643',
        K: '\u062E',
        l: '\u0644',
        L: '\u0612',
        m: '\u0645',
        M: '\u0627',
        n: '\u0646',
        N: '\u06BA',
        o: '\u06D5',
        O: '\u0629',
        p: '\u067E',
        P: '\u06E5',
        q: '\u0642',
        Q: '\u06E5',
        r: '\u0631',
        R: '\u0691',
        s: '\u0633',
        S: '\u0635',
        t: '\u062A',
        T: '\u0679',
        u: '\u06F6',
        U: '\u060C',
        v: '\u0637',
        V: '\u0638',
        w: '\u0648',
        W: '\u0610',
        x: '\u0634',
        X: '\u0698',
        y: '\u06D2',
        Y: '\u0601',
        z: '\u0632',
        Z: '\u0630',

        '"': '\u0640',
        "'": '\u06D4',
        ';': '\u061B',
        '?': '\u061F',
        ',': '\u060C',
        '>': '\u060D',
        '<': '\u060D',
        '{': '\u0657',
        '}': '\u064F',
        '/': '\u06DF',

        '0': '\u0660',
        '1': '\u0661',
        '2': '\u0662',
        '3': '\u0663',
        '4': '\u0664',
        '5': '\u0665',
        '6': '\u0666',
        '7': '\u0667',
        '8': '\u0668',
        '9': '\u0669',

    };

    function isToggleEvent(event) {
        return event.ctrlKey && String.fromCharCode(event.charCode) == "'";
    };

    function enable() {

        widget.onkeypress = keypressEnabled;
        if (button) {
            button.innerHTML = 'turn urdu off';
            button.style.borderStyle = 'inset';
            button.onclick = disable;
            widget.focus();
        }
    };

    function disable() {
        widget.style.background = 'white';
        widget.onkeypress = keypressDisabled;
        if (button) {
            button.innerHTML = 'turn urdu on';
            button.style.borderStyle = 'outset';
            button.onclick = enable;
            widget.focus();
        }
    };

    function keypressEnabled(event) {
        if (isToggleEvent(event)) {
            disable();
            return;
        }
        var char = String.fromCharCode(event.charCode);
        var pos = widget.selectionStart;

        if (widget.urduDigraph) {
            var urdu = URDU_MAP[widget.urduDigraph + char];
            // no matter what, we're leaving digraph mode on this keypress.
            widget.urduDigraph = null;
            if (urdu) {
                widget.value = widget.value.substr(0, widget.selectionStart - 1) +
                    urdu +
                    widget.value.substr(widget.selectionEnd);
                widget.setSelectionRange(pos + 1, pos + 1);
                return false;
            } else if (char == ' ') {
                // digraph char followed by space => eat the space.
                return false;
            }
        }

        if (URDU_MAP[char + 'h'] || URDU_MAP[char + '`']) { // potential digraph!
            widget.urduDigraph = char;
        }

        var urdu = URDU_MAP[char];
        if (urdu) {
            widget.value = widget.value.substr(0, widget.selectionStart) +
                urdu +
                widget.value.substr(widget.selectionEnd);
            widget.setSelectionRange(pos + 1, pos + 1);
            return false;
        }
        return true;
    };

    function keypressDisabled(event) {
        if (isToggleEvent(event)) {
            enable();
            return false;
        }
        return true;
    };


    widget.urduBound = true;
    if (startenable) enable();
    else disable();
};

// eof
