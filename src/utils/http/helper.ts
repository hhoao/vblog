import axios from 'axios';

export async function urlStat(url) {
  return await axios
    .get(url)
    .then(() => {
      return true;
    })
    .catch(() => {
      return false;
    });
}
