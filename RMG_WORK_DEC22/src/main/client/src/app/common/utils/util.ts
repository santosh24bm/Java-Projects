export function generateToken(range:number) {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    for (var i = 0; i < range; i++)
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    return text;
  }